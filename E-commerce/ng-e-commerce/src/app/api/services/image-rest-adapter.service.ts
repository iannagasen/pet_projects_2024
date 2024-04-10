/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { getImage$Jpeg } from '../fn/image-rest-adapter/get-image-jpeg';
import { GetImage$Jpeg$Params } from '../fn/image-rest-adapter/get-image-jpeg';
import { getImage$Png } from '../fn/image-rest-adapter/get-image-png';
import { GetImage$Png$Params } from '../fn/image-rest-adapter/get-image-png';
import { uploadImage } from '../fn/image-rest-adapter/upload-image';
import { UploadImage$Params } from '../fn/image-rest-adapter/upload-image';

@Injectable({ providedIn: 'root' })
export class ImageRestAdapterService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `uploadImage()` */
  static readonly UploadImagePath = '/image';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `uploadImage()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  uploadImage$Response(params: UploadImage$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return uploadImage(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `uploadImage$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  uploadImage(params: UploadImage$Params, context?: HttpContext): Observable<number> {
    return this.uploadImage$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `getImage()` */
  static readonly GetImagePath = '/image/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getImage$Jpeg()` instead.
   *
   * This method doesn't expect any request body.
   */
  getImage$Jpeg$Response(params: GetImage$Jpeg$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<string>>> {
    return getImage$Jpeg(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getImage$Jpeg$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getImage$Jpeg(params: GetImage$Jpeg$Params, context?: HttpContext): Observable<Array<string>> {
    return this.getImage$Jpeg$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<string>>): Array<string> => r.body)
    );
  }

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getImage$Png()` instead.
   *
   * This method doesn't expect any request body.
   */
  getImage$Png$Response(params: GetImage$Png$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<string>>> {
    return getImage$Png(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getImage$Png$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getImage$Png(params: GetImage$Png$Params, context?: HttpContext): Observable<Array<string>> {
    return this.getImage$Png$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<string>>): Array<string> => r.body)
    );
  }

}
