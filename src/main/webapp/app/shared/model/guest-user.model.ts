import { ICar } from 'app/shared/model//car.model';
import { IGRole } from 'app/shared/model//g-role.model';

export interface IGuestUser {
    id?: number;
    name?: string;
    phone?: string;
    cars?: ICar[];
    gRoles?: IGRole[];
}

export class GuestUser implements IGuestUser {
    constructor(public id?: number, public name?: string, public phone?: string, public cars?: ICar[], public gRoles?: IGRole[]) {}
}
