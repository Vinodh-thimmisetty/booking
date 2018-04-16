import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';


/**
 *
 * Route Mapping for Entire Application
 *
 * @author Vinodh Kumar Thimmisetty
 *
 */


const appRoutes: Routes = [

  {path: '/', component: AppComponent}

];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
