/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { GuestUserDetailComponent } from 'app/entities/guest-user/guest-user-detail.component';
import { GuestUser } from 'app/shared/model/guest-user.model';

describe('Component Tests', () => {
    describe('GuestUser Management Detail Component', () => {
        let comp: GuestUserDetailComponent;
        let fixture: ComponentFixture<GuestUserDetailComponent>;
        const route = ({ data: of({ guestUser: new GuestUser(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [GuestUserDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GuestUserDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GuestUserDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.guestUser).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
