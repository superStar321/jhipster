import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGRole } from 'app/shared/model/g-role.model';

@Component({
    selector: 'jhi-g-role-detail',
    templateUrl: './g-role-detail.component.html'
})
export class GRoleDetailComponent implements OnInit {
    gRole: IGRole;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ gRole }) => {
            this.gRole = gRole;
        });
    }

    previousState() {
        window.history.back();
    }
}
