import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import {UserComponent} from "./user.component";
import {ReactiveFormsModule} from "@angular/forms";
import {NzFormModule} from "ng-zorro-antd/form";
import {NzSelectModule} from "ng-zorro-antd/select";
import {NzCardModule} from "ng-zorro-antd/card";
import {NzTableModule} from "ng-zorro-antd/table";
import {NzButtonModule} from "ng-zorro-antd/button";
import {NzIconModule} from "ng-zorro-antd/icon";
import {NzDividerModule} from "ng-zorro-antd/divider";
import {NzModalModule} from "ng-zorro-antd/modal";
import {NzMessageModule} from "ng-zorro-antd/message";
import { UserFormComponent } from './user-form/user-form.component';
import {NzInputModule} from "ng-zorro-antd/input";
import {NzInputNumberModule} from "ng-zorro-antd/input-number";


@NgModule({
  declarations: [UserComponent, UserFormComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule,
    NzFormModule,
    NzSelectModule,
    NzCardModule,
    NzTableModule,
    NzButtonModule,
    NzIconModule,
    NzDividerModule,
    NzModalModule,
    NzMessageModule,
    NzInputModule,
    NzInputNumberModule
  ]
})
export class UserModule { }
