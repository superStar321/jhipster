import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGuestRole } from 'app/shared/model/guest-role.model';

@Component({
    selector: 'jhi-guest-role-detail',
    templateUrl: './guest-role-detail.component.html'
})
export class GuestRoleDetailComponent implements OnInit {
    guestRole: IGuestRole;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ guestRole }) => {
            this.guestRole = guestRole;
        });
    }

    previousState() {
        window.history.back();
    }
}
