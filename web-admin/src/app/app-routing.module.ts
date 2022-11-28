import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [{path: '', redirectTo: "/route", pathMatch: "full"},
  {path: 'route', loadChildren: () => import('./route/route.module').then(m => m.RouteModule)},
  {path: '**', redirectTo: '/route'}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
