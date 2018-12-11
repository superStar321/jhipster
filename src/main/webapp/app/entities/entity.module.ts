import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterGuestUserModule } from './guest-user/guest-user.module';
import { JhipsterCarModule } from './car/car.module';
import { JhipsterGuestRoleModule } from './guest-role/guest-role.module';
import { JhipsterGRoleModule } from './g-role/g-role.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JhipsterGuestUserModule,
        JhipsterCarModule,
        JhipsterGuestRoleModule,
        JhipsterGRoleModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEntityModule {}
