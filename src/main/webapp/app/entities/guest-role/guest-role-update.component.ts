import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IGuestRole } from 'app/shared/model/guest-role.model';
import { GuestRoleService } from './guest-role.service';

@Component({
    selector: 'jhi-guest-role-update',
    templateUrl: './guest-role-update.component.html'
})
export class GuestRoleUpdateComponent implements OnInit {
    guestRole: IGuestRole;
    isSaving: boolean;

    constructor(private guestRoleService: GuestRoleService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ guestRole }) => {
            this.guestRole = guestRole;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.guestRole.id !== undefined) {
            this.subscribeToSaveResponse(this.guestRoleService.update(this.guestRole));
        } else {
            this.subscribeToSaveResponse(this.guestRoleService.create(this.guestRole));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGuestRole>>) {
        result.subscribe((res: HttpResponse<IGuestRole>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
