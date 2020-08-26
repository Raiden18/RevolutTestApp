
import com.google.gson.annotations.SerializedName

data class CurrencyRateValues(
    @SerializedName("rates")
    val rates: Rates?
)