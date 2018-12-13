package com.amadoutirera.maakomome.domain

sealed class Failure {
    class NetworkConnection: Failure()
    class ServerError: Failure()
    class FeatureFailure: Failure()
}
