import {Component, OnInit} from '@angular/core';
import {CartItem} from "../../common/cart-item";
import {CartService} from "../../services/cart.service";

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.scss']
})
export class CartDetailsComponent implements OnInit {
  cartItems: CartItem[] = [];
  totalPrice = 0;
  totalQuantity = 0;

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.listCartDetails();
  }

  listCartDetails() {
    // get a handle to the cart items
    this.cartItems = this.cartService.cartItems;
    // subscribe to the cart totalPrice
    this.cartService.totalPrice.subscribe(data => this.totalPrice = data);
    // subscribe to the cart totalQuantity
    this.cartService.totalQuantity.subscribe(data => this.totalQuantity = data);
    // compute cart total price and total quantity
    this.cartService.calculateTotals();
  }

  incrementQuantity(item: CartItem) {
    this.cartService.addToCart(item);
  }

  decrementQuantity(item: CartItem) {
    this.cartService.decrementQuantity(item);
  }

  remove(cartItem: CartItem) {
    const index = this.cartItems.findIndex(tempCartItem => tempCartItem.id);
    if (index > -1) {
      this.cartItems.splice(index, 1);
    }
  }
}
