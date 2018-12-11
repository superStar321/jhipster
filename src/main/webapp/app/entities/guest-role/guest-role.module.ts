import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared';
import {
    GuestRoleComponent,
    GuestRoleDetailComponent,
    GuestRoleUpdateComponent,
    GuestRoleDeletePopupComponent,
    GuestRoleDeleteDialogComponent,
    guestRoleRoute,
    guestRolePopupRoute
} from './';

const ENTITY_STATES = [...guestRoleRoute, ...guestRolePopupRoute];

@NgModule({
    imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GuestRoleComponent,
        GuestRoleDetailComponent,
        GuestRoleUpdateComponent,
        GuestRoleDeleteDialogComponent,
        GuestRoleDeletePopupComponent
    ],
    entryComponents: [GuestRoleComponent, GuestRoleUpdateComponent, GuestRoleDeleteDialogComponent, GuestRoleDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterGuestRoleModule {}
