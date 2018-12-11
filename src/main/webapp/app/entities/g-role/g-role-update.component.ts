import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IGRole } from 'app/shared/model/g-role.model';
import { GRoleService } from './g-role.service';
import { IGuestUser } from 'app/shared/model/guest-user.model';
import { GuestUserService } from 'app/entities/guest-user';
import { IGuestRole } from 'app/shared/model/guest-role.model';
import { GuestRoleService } from 'app/entities/guest-role';

@Component({
    selector: 'jhi-g-role-update',
    templateUrl: './g-role-update.component.html'
})
export class GRoleUpdateComponent implements OnInit {
    gRole: IGRole;
    isSaving: boolean;

    guestusers: IGuestUser[];

    guestroles: IGuestRole[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private gRoleService: GRoleService,
        private guestUserService: GuestUserService,
        private guestRoleService: GuestRoleService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ gRole }) => {
            this.gRole = gRole;
        });
        this.guestUserService.query().subscribe(
            (res: HttpResponse<IGuestUser[]>) => {
                this.guestusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.guestRoleService.query().subscribe(
            (res: HttpResponse<IGuestRole[]>) => {
                this.guestroles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.gRole.id !== undefined) {
            this.subscribeToSaveResponse(this.gRoleService.update(this.gRole));
        } else {
            this.subscribeToSaveResponse(this.gRoleService.create(this.gRole));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGRole>>) {
        result.subscribe((res: HttpResponse<IGRole>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackGuestUserById(index: number, item: IGuestUser) {
        return item.id;
    }

    trackGuestRoleById(index: number, item: IGuestRole) {
        return item.id;
    }
}
