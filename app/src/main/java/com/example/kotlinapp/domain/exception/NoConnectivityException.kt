package com.example.kotlinapp.domain.exception

/**
 * Thrown when a network operation is attempted with no active internet
 * connection. Lives in [domain] (not [data]) because "no connectivity" is
 * a business-meaningful outcome the UI needs to react to (e.g. show a
 * dedicated "You're offline" state) — not just a generic IOException.
 */
class NoConnectivityException : Exception("No internet connection available")
