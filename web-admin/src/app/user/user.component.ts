import {Component, OnInit} from '@angular/core';
import {BaseJsonRsp} from "../common/domain/BaseJsonRsp";
import {environment} from "../../environments/environment";
import {PageJsonRsp} from "../common/domain/PageJsonRsp";
import {HttpClient} from "@angular/common/http";
import {NzModalService} from "ng-zorro-antd/modal";
import {NzMessageService} from "ng-zorro-antd/message";

export interface User {
  username: string;
  fullname: string;
  remark: string;
  status: number;
  //status string类型
  statusString: string;
  //status 说明
  statusRemark: string;
  supper: number;
  //supper string类型
  supperString: string;
  // supper 说明
  supperRemark: string;
}


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  dataList: User[] = [];
  page = 1;
  limit = 10;
  total = 0;


  constructor(private http: HttpClient, private modal: NzModalService, private message: NzMessageService) {

  }


  ngOnInit(): void {
  }

  query(page: number, limit: number): void {
    this.http
      .get<PageJsonRsp<User>>(environment.contextPath + "auth/user/page", {
        params: {
          page: page,
          limit: limit
        }
      })
      .subscribe(pageData => {
        pageData.data.map(data => {
          data.statusRemark = data.status == 1 ? "生效" : "失效"
          data.supperRemark = data.supper == 1 ? "是" : "否"
        });
        this.dataList = pageData.data;
        this.page = page;
        this.limit = limit;
        this.total = pageData.total;
      });
  }

  delete(username: string): void {
    this.modal.confirm({
      nzTitle: '确定要删除吗?',
      nzOkText: '确定',
      nzOkType: 'primary',
      nzOkDanger: true,
      nzOnOk: () => {
        this.http.delete<BaseJsonRsp>(environment.contextPath + "auth/user/delete", {body: {username: username}})
          .subscribe((data) => {
            if (data.success) {
              this.query(this.page, this.limit)
            } else {
              this.message.create("error", data.errMsg)
            }
          })
      },
      nzCancelText: '取消',
    });
  }

  edit(param: User): void {

  }

  add(): void {

  }

}
