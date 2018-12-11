import { IGuestUser } from 'app/shared/model//guest-user.model';
import { IGuestRole } from 'app/shared/model//guest-role.model';

export interface IGRole {
    id?: number;
    guestUser?: IGuestUser;
    guestRole?: IGuestRole;
}

export class GRole implements IGRole {
    constructor(public id?: number, public guestUser?: IGuestUser, public guestRole?: IGuestRole) {}
}
