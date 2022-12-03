import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NzModalService} from "ng-zorro-antd/modal";
import {environment} from "../../environments/environment";
import {RouteFormComponent} from "./route-form/route-form.component";
import {toNumber} from "ng-zorro-antd/core/util";

export interface Route {
  id: string;
  predicates: string;
  filters: string;
  uri: string;
  orders: number;
  description: string;
  status: number;
  statusString: string;
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
      .get<PageJsonRsp>(environment.contextPath + "auth/route/page", {
        params: {
          page: page,
          limit: limit
        }
      })
      .subscribe(pageData => {
        this.dataList = pageData.data;
        this.dataList.map(data => {
          data.statusRemark = data.status == 1 ? "生效" : "失效"
          data.statusString = String(data.status);
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
        this.http.delete<BaseJsonRsp>(environment.contextPath + "auth/route/delete", {
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

  edit(param: Route): void {
    this.modal.create({
      nzTitle: '修改',
      nzOkText: '确定',
      nzContent: RouteFormComponent,
      nzComponentParams: {
        param
      },
      nzOnOk: (instance) => {
        const route = instance.routeForm.value;
        route.status = toNumber(instance.routeForm.value.statusString);
        this.http.put<BaseJsonRsp>(environment.contextPath + "auth/route/edit", route
        )
          .subscribe(() => {
            this.query(this.page, this.limit)
          })
      },
      nzCancelText: '取消',
    })
  }

}
