import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavComponent } from './components/nav/nav.component';
import { MenuComponent } from './components/menu/menu.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [NavComponent, MenuComponent]
})
export class SharedModule { }
