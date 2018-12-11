import { IGuestUser } from 'app/shared/model//guest-user.model';

export interface ICar {
    id?: number;
    carName?: string;
    carBrand?: string;
    guestUser?: IGuestUser;
}

export class Car implements ICar {
    constructor(public id?: number, public carName?: string, public carBrand?: string, public guestUser?: IGuestUser) {}
}
