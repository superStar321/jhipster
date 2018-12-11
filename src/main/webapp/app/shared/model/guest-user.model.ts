import { ICar } from 'app/shared/model//car.model';
import { IGRole } from 'app/shared/model//g-role.model';

export interface IGuestUser {
    id?: number;
    name?: string;
    phone?: string;
    age?: number;
    sex?: string;
    isable?: number;
    cars?: ICar[];
    gRoles?: IGRole[];
}

export class GuestUser implements IGuestUser {
    constructor(
        public id?: number,
        public name?: string,
        public phone?: string,
        public age?: number,
        public sex?: string,
        public isable?: number,
        public cars?: ICar[],
        public gRoles?: IGRole[]
    ) {}
}
