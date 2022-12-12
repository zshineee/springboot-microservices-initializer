import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NzModalService} from "ng-zorro-antd/modal";
import {environment} from "../../environments/environment";
import {RouteFormComponent} from "./route-form/route-form.component";
import {toNumber} from "ng-zorro-antd/core/util";
import {UntypedFormBuilder, UntypedFormControl, UntypedFormGroup} from "@angular/forms";
import {PageJsonRsp} from "../common/domain/PageJsonRsp";
import {BaseJsonRsp} from "../common/domain/BaseJsonRsp";

export interface Route {
  id: string;
  predicates: string;
  filters: string;
  uri: string;
  orders: number;
  description: string;
  status: number;
  //status string类型
  statusString: string;
  //说明
  statusRemark: string;

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
  validateForm!: UntypedFormGroup;

  constructor(private http: HttpClient, private modal: NzModalService, private fb: UntypedFormBuilder) {
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({});
    this.validateForm.addControl('status', new UntypedFormControl());
  }


  reset(): void {
    this.validateForm.reset();
    this.page = 1;
    this.limit = 10;
    this.total = 0;
    this.query(this.page, this.limit);
  }


  query(page: number, limit: number): void {
    const status = this.validateForm.controls['status'].value;
    this.http
      .get<PageJsonRsp<Route>>(environment.contextPath + "auth/route/page", {
        params: {
          page: page,
          limit: limit,
          status: status == null ? '' : status
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
        this.http.delete<BaseJsonRsp>(environment.contextPath + "auth/route/delete", {body: {id: id}}
        )
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

  add(): void {
    this.modal.create({
      nzTitle: '修改',
      nzOkText: '确定',
      nzContent: RouteFormComponent,
      nzComponentParams: {},
      nzOnOk: (instance) => {
        const route = instance.routeForm.value;
        route.status = toNumber(instance.routeForm.value.status);
        this.http.post<BaseJsonRsp>(environment.contextPath + "auth/route/add", route
        )
          .subscribe(() => {
            this.query(this.page, this.limit)
          })
      },
      nzCancelText: '取消',
    })
  }

}
