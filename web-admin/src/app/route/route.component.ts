import {Component, OnInit} from '@angular/core';
import {NzTableQueryParams} from "ng-zorro-antd/table";
import {HttpClient} from "@angular/common/http";
import {NzModalService} from "ng-zorro-antd/modal";

interface Route {
  id: string;
  predicates: string;
  filters: string;
  uri: string;
  orders: number;
  description: string;
  status: number;
  statusRemark: string;
}

interface PageJsonRsp {
  pages: number,
  total: number,
  data: Route[];
  success: boolean;
  errMsg: string;
}

interface BaseJsonRsp {
  success: boolean;
  errMsg: string;
}

@Component({
  selector: 'app-route',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css']
})
export class RouteComponent implements OnInit {

  dataList: Route[] = [];
  page = 1;
  limit = 10;
  total = 0;


  constructor(private http: HttpClient, private modal: NzModalService) {
  }

  ngOnInit(): void {
  }

  query(page: number, limit: number): void {
    this.http
      .get<PageJsonRsp>("/auth/route/page", {
        params: {
          page: page,
          limit: limit
        }
      })
      .subscribe(pageData => {
        this.dataList = pageData.data;
        this.dataList.map(data => {
          data.statusRemark = data.status == 1 ? "生效" : "失效"
        });
        this.page = page;
        this.limit = limit;
        this.total = pageData.total;
      });
  }

  delete(id: string): void {
    this.modal.confirm({
      nzTitle: '确定要删除吗?',
      nzOkText: '确定',
      nzOkType: 'primary',
      nzOkDanger: true,
      nzOnOk: () => {
        this.http.delete<BaseJsonRsp>("/auth/route/delete", {
          params: {
            id: id
          }
        })
          .subscribe(() => {
            this.query(this.page, this.limit)
          })
      },
      nzCancelText: '取消',
    });
  }

}
