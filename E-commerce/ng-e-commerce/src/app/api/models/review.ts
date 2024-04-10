/* tslint:disable */
/* eslint-disable */
import { Rating } from '../models/rating';
export interface Review {
  date?: string;
  id?: number;
  imagePaths?: Array<string>;
  rating?: Rating;
  user?: string;
}
