import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouteRoutingModule } from './route-routing.module';
import { RouteComponent } from './route.component';
import {NzTableModule} from "ng-zorro-antd/table";
import {NzDividerModule} from "ng-zorro-antd/divider";
import {NzModalModule} from "ng-zorro-antd/modal";


@NgModule({
  declarations: [
    RouteComponent
  ],
  imports: [
    CommonModule,
    RouteRoutingModule,
    NzTableModule,
    NzDividerModule,
    NzModalModule
  ]
})
export class RouteModule { }
