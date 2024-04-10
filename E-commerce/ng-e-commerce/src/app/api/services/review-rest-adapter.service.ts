/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { getAllReviews } from '../fn/review-rest-adapter/get-all-reviews';
import { GetAllReviews$Params } from '../fn/review-rest-adapter/get-all-reviews';
import { getReviews } from '../fn/review-rest-adapter/get-reviews';
import { GetReviews$Params } from '../fn/review-rest-adapter/get-reviews';
import { Reviews } from '../models/reviews';

@Injectable({ providedIn: 'root' })
export class ReviewRestAdapterService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getAllReviews()` */
  static readonly GetAllReviewsPath = '/review';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllReviews()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllReviews$Response(params?: GetAllReviews$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Reviews>>> {
    return getAllReviews(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllReviews$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllReviews(params?: GetAllReviews$Params, context?: HttpContext): Observable<Array<Reviews>> {
    return this.getAllReviews$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<Reviews>>): Array<Reviews> => r.body)
    );
  }

  /** Path part for operation `getReviews()` */
  static readonly GetReviewsPath = '/review/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getReviews()` instead.
   *
   * This method doesn't expect any request body.
   */
  getReviews$Response(params: GetReviews$Params, context?: HttpContext): Observable<StrictHttpResponse<Reviews>> {
    return getReviews(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getReviews$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getReviews(params: GetReviews$Params, context?: HttpContext): Observable<Reviews> {
    return this.getReviews$Response(params, context).pipe(
      map((r: StrictHttpResponse<Reviews>): Reviews => r.body)
    );
  }

}
