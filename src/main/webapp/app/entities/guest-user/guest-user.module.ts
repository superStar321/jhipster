import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared';
import {
    GuestUserComponent,
    GuestUserDetailComponent,
    GuestUserUpdateComponent,
    GuestUserDeletePopupComponent,
    GuestUserDeleteDialogComponent,
    guestUserRoute,
    guestUserPopupRoute
} from './';

const ENTITY_STATES = [...guestUserRoute, ...guestUserPopupRoute];

@NgModule({
    imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GuestUserComponent,
        GuestUserDetailComponent,
        GuestUserUpdateComponent,
        GuestUserDeleteDialogComponent,
        GuestUserDeletePopupComponent
    ],
    entryComponents: [GuestUserComponent, GuestUserUpdateComponent, GuestUserDeleteDialogComponent, GuestUserDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterGuestUserModule {}
