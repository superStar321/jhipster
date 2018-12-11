import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared';
import {
    GRoleComponent,
    GRoleDetailComponent,
    GRoleUpdateComponent,
    GRoleDeletePopupComponent,
    GRoleDeleteDialogComponent,
    gRoleRoute,
    gRolePopupRoute
} from './';

const ENTITY_STATES = [...gRoleRoute, ...gRolePopupRoute];

@NgModule({
    imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [GRoleComponent, GRoleDetailComponent, GRoleUpdateComponent, GRoleDeleteDialogComponent, GRoleDeletePopupComponent],
    entryComponents: [GRoleComponent, GRoleUpdateComponent, GRoleDeleteDialogComponent, GRoleDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterGRoleModule {}
