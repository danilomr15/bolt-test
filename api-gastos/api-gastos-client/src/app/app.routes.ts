import { Routes } from '@angular/router';
import { GastosComponent } from './gastos/gastos.component';
import { TagsComponent } from './tags/tags.component';
import { RelatorioComponent } from './relatorio/relatorio.component';

export const routes: Routes = 
[
    { path: '', redirectTo: '/gastos', pathMatch: 'full' },
    { path: 'gastos', component: GastosComponent },
    { path: 'tags', component: TagsComponent },
    { path: 'relatorio', component: RelatorioComponent }
];
