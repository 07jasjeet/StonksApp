package com.example.stonksapp.utils

/** This [Mock] object should be passed to a data class's constructor to get a default mock
 * instance to be used for a compose preview. To use this object, do as follows:
 *
 * 1. Create a mock for a data class as follows:
 *      ```
 *      data class Model(
 *          value: String,
 *          another: String
 *      ) {
 *          constructor(mock: Mock): this(
 *              value = "My mock value",
 *              another = "Another Mock"
 *          )
 *      }
 *      ```
 * 2. Now, to use the defined mock:
 *      ```
 *      val mockModel = Model(Mock)
 *
 *      // Use mock values in preview or anywhere else.
 *      ```
 * For complex data classes with nested data classes, mocks can easily be prepared with the
 * help of the above method as well.
 *
 * <br></br>
 *
 * For flexibility, consider using the following practice:
 * ```
 * val flexibleMockModel = Model(Mock).copy(value = "Some other value")
 * ```
 */
object Mock