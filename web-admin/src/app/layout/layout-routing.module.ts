import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [{path: '', redirectTo: "/route", pathMatch: "full"},
  {path: 'route', loadChildren: () => import('../route/route.module').then(m => m.RouteModule)},
  {path: 'user', loadChildren: () => import('../user/user.module').then(m => m.UserModule)},
  {path: '**', redirectTo: '/route'}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule { }
