import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LayoutComponent} from "./layout.component";

const routes: Routes = [
  {
    path: "", component: LayoutComponent,
    children: [
      { path: '', redirectTo: 'setting/route', pathMatch: 'full' },
      {path: 'setting/route', loadChildren: () => import('../route/route.module').then(m => m.RouteModule)},
      {path: 'auth/user', loadChildren: () => import('../user/user.module').then(m => m.UserModule)},
    ]
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule {
}
