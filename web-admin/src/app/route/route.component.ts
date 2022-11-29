import {Component, OnInit} from '@angular/core';
import {NzTableQueryParams} from "ng-zorro-antd/table";
import {HttpClient} from "@angular/common/http";

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


  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
  }

  list(params: NzTableQueryParams): void {
    this.http
      .get<PageJsonRsp>("/auth/route/page", {params: {page: params.pageIndex, limit: params.pageSize}})
      .subscribe(pageData => {
        this.dataList = pageData.data;
        this.dataList.map(data => {
          data.statusRemark = data.status == 1 ? "生效" : "失效"
        });
        this.page = params.pageIndex;
        this.limit = params.pageSize;
        this.total = pageData.total;
      });
  }

}
