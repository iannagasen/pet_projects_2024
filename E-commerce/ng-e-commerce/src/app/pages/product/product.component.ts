import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ProductRestAdapterService } from 'src/app/api/services';
import { Product } from 'src/app/api/models';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product.component.html',
})
export class ProductComponent implements OnInit {

  productId!: number;
  product!: Product;

  constructor(
    private _route: ActivatedRoute,
    private _productRestService: ProductRestAdapterService
  ) { }

  ngOnInit(): void {
    this._route.params.subscribe(params => {
      this.productId = params['productId'];
    })

    this._productRestService.getProduct({
      id: this.productId
    }).subscribe({
      next: (res) => {
        this.product = res
        console.log(this.product)
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

}
