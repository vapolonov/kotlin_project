package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.products.CreateProductRequest
import backend.api.models.products.CreateProductResponse
import backend.helpers.AuthorizationHelper
import backend.helpers.GarbageCollector
import io.qameta.allure.Step
import okhttp3.ResponseBody
import retrofit2.Response

open class ProductsController : Endpoints() {
  private val authHelper = AuthorizationHelper()

  @Step("Get all products")
  fun getAllProducts(
    token: String = authHelper.getAdminToken(),
    offset: Int = 0,
    limit: Int = 50
  ): Response<CreateProductResponse> {
    return productsApi.getAllProducts(token, offset, limit).execute()
  }

  @Step("Get product by id")
  fun getProductById(token: String = authHelper.getAdminToken(), id: Int): Response<CreateProductResponse> {
    return productsApi.getProductById(token, id).execute()
  }

  @Step("Create new product")
  fun createProduct(
    token: String = authHelper.getAdminToken(),
    body: CreateProductRequest
  ): Response<CreateProductResponse> {
    return productsApi.postProductCreate(token, body).execute()
      .also { GarbageCollector.products.add(it.getAsObject().id) }
  }

  @Step("Delete product by id")
  fun deleteProductById(token: String = authHelper.getAdminToken(), id: Int): Response<ResponseBody> {
    return productsApi.deleteProduct(token, id).execute()
  }
}