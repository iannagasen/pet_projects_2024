/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { getAll } from '../fn/product-rest-adapter/get-all';
import { GetAll$Params } from '../fn/product-rest-adapter/get-all';
import { getProduct } from '../fn/product-rest-adapter/get-product';
import { GetProduct$Params } from '../fn/product-rest-adapter/get-product';
import { Product } from '../models/product';

@Injectable({ providedIn: 'root' })
export class ProductRestAdapterService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getAll()` */
  static readonly GetAllPath = '/product';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAll()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll$Response(params?: GetAll$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Product>>> {
    return getAll(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAll$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll(params?: GetAll$Params, context?: HttpContext): Observable<Array<Product>> {
    return this.getAll$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<Product>>): Array<Product> => r.body)
    );
  }

  /** Path part for operation `getProduct()` */
  static readonly GetProductPath = '/product/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getProduct()` instead.
   *
   * This method doesn't expect any request body.
   */
  getProduct$Response(params: GetProduct$Params, context?: HttpContext): Observable<StrictHttpResponse<Product>> {
    return getProduct(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getProduct$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getProduct(params: GetProduct$Params, context?: HttpContext): Observable<Product> {
    return this.getProduct$Response(params, context).pipe(
      map((r: StrictHttpResponse<Product>): Product => r.body)
    );
  }

}
