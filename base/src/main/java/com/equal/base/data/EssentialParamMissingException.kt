package com.equal.base.data

/**
 * Created by Thoai Nguyen on 3/4/20.
 */
/**
 * Exception thrown when an essential parameter is missing in the backend/network response.
 */
class EssentialParamMissingException(
    missingParam: String,
    rawObject: Any
) : RuntimeException("The params: $missingParam are missing in received object: $rawObject")