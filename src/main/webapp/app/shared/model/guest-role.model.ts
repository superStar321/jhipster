import { IGRole } from 'app/shared/model//g-role.model';

export interface IGuestRole {
    id?: number;
    roleName?: string;
    gRoles?: IGRole[];
}

export class GuestRole implements IGuestRole {
    constructor(public id?: number, public roleName?: string, public gRoles?: IGRole[]) {}
}
