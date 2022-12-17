import {Injectable} from '@angular/core';
import {BaseJsonRsp} from "../domain/BaseJsonRsp";
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {NzMessageService} from "ng-zorro-antd/message";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient, private message: NzMessageService) {
  }



  post(url: string, body: any): boolean {
    return this.http.post<BaseJsonRsp>(environment.contextPath + url, body
    ).subscribe((data) => {
      if (data.success) {
        return true;
      } else {
        this.message.error(data.errMsg)
        return false;
      }
    }).closed;
  }
}
