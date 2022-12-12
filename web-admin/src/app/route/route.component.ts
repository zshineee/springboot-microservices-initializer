import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NzModalService} from "ng-zorro-antd/modal";
import {environment} from "../../environments/environment";
import {RouteFormComponent} from "./route-form/route-form.component";
import {toNumber} from "ng-zorro-antd/core/util";
import {UntypedFormBuilder, UntypedFormControl, UntypedFormGroup} from "@angular/forms";

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


@Component({
  selector: 'app-route',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css']
})
export class RouteComponent implements OnInit {

  //是否关闭
  isCollapse = true;
  dataList: Route[] = [];
  page = 1;
  limit = 10;
  total = 0;
  controlArray: Array<{ index: number; show: boolean; name: string; id: string }> = [];
  tableNames = [{name: '服务名', id: 'id'}, {name: '判定器', id: 'predicates'}, {
    name: '过滤器',
    id: 'filters'
  }, {name: '路径', id: 'uri'}, {name: '优先级', id: 'orders'}, {name: '状态', id: 'statusString'}, {
    name: '说明',
    id: 'description'
  }];
  route?: Route;
  detail = this.searchLayoutDetail();
  validateForm!: UntypedFormGroup;

  constructor(private http: HttpClient, private modal: NzModalService, private fb: UntypedFormBuilder) {
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({});
    for (let i = 0; i < this.detail.total - this.detail.blank; i++) {
      this.controlArray.push({
        index: i,
        show: i < this.detail.show,
        name: this.tableNames[i].name,
        id: this.tableNames[i].id
      });
      this.validateForm.addControl(this.tableNames[i].id, new UntypedFormControl());
    }
  }

  toggleCollapse() {
    this.isCollapse = !this.isCollapse;
    this.controlArray.forEach((c, index) => {
      c.show = !this.isCollapse ? true : index < this.detail.show;
      if (c.show) {
        this.validateForm.controls[c.id].setValue("")
      }
    });
  }

  searchLayoutDetail(): { total: number, show: number, blank: number } {

    const blank = 2 - this.tableNames.length % 3;

    return {
      blank: blank,
      total: this.tableNames.length + blank,
      show: this.tableNames.length < 3 ? this.tableNames.length : 2
    }

  }

  reset(): void {
    this.validateForm.reset();
    this.page = 1;
    this.limit = 10;
    this.total = 0;
    this.route = undefined;
    this.query(this.page, this.limit);
  }


  query(page: number, limit: number): void {
    this.route = {
      id: this.validateForm.controls.hasOwnProperty('id') ? this.validateForm.controls['id'].value : null,
      predicates: this.validateForm.controls.hasOwnProperty('predicates') ? this.validateForm.controls['predicates'].value : null,
      filters: this.validateForm.controls.hasOwnProperty('filters') ? this.validateForm.controls['filters'].value : null,
      description: this.validateForm.controls.hasOwnProperty('description') ? this.validateForm.controls['description'].value : null,
      orders: this.validateForm.controls.hasOwnProperty('orders') ? this.validateForm.controls['orders'].value : null,
      status: this.validateForm.controls.hasOwnProperty('statusString') ? this.validateForm.controls['statusString'].value : null,
      statusRemark: this.validateForm.controls.hasOwnProperty('statusRemark') ? this.validateForm.controls['statusRemark'].value : null,
      statusString: this.validateForm.controls.hasOwnProperty('statusString') ? this.validateForm.controls['statusString'].value : null,
      uri: this.validateForm.controls.hasOwnProperty('uri') ? this.validateForm.controls['uri'].value : null
    };

    this.http
      .get<PageJsonRsp<Route>>(environment.contextPath + "auth/route/page", {
        params: {
          page: page,
          limit: limit,
          id: this.route.id,
          predicates: this.route.predicates,
          filters: this.route.filters,
          uri: this.route.uri,
          orders: this.route.orders,
          description: this.route.description,
          status: this.route.status
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
