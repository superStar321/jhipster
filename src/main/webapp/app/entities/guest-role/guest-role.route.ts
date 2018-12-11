import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GuestRole } from 'app/shared/model/guest-role.model';
import { GuestRoleService } from './guest-role.service';
import { GuestRoleComponent } from './guest-role.component';
import { GuestRoleDetailComponent } from './guest-role-detail.component';
import { GuestRoleUpdateComponent } from './guest-role-update.component';
import { GuestRoleDeletePopupComponent } from './guest-role-delete-dialog.component';
import { IGuestRole } from 'app/shared/model/guest-role.model';

@Injectable({ providedIn: 'root' })
export class GuestRoleResolve implements Resolve<IGuestRole> {
    constructor(private service: GuestRoleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<GuestRole> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<GuestRole>) => response.ok),
                map((guestRole: HttpResponse<GuestRole>) => guestRole.body)
            );
        }
        return of(new GuestRole());
    }
}

export const guestRoleRoute: Routes = [
    {
        path: 'guest-role',
        component: GuestRoleComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.guestRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'guest-role/:id/view',
        component: GuestRoleDetailComponent,
        resolve: {
            guestRole: GuestRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.guestRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'guest-role/new',
        component: GuestRoleUpdateComponent,
        resolve: {
            guestRole: GuestRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.guestRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'guest-role/:id/edit',
        component: GuestRoleUpdateComponent,
        resolve: {
            guestRole: GuestRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.guestRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const guestRolePopupRoute: Routes = [
    {
        path: 'guest-role/:id/delete',
        component: GuestRoleDeletePopupComponent,
        resolve: {
            guestRole: GuestRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.guestRole.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
