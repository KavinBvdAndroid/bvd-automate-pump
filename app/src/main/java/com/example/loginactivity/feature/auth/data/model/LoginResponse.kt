package com.example.loginactivity.feature.auth.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize


@Parcelize
data class LoginResponse(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("refresh_token")
	val refreshToken: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("twofactor_accepted")
	val twofactorAccepted: Int? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null,

	@field:SerializedName("expires_in")
	val expiresIn: Int? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("term_accepted")
	val termAccepted: Int? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int
	) {
	}

	companion object : Parceler<LoginResponse> {

		override fun LoginResponse.write(parcel: Parcel, flags: Int) {
			parcel.writeString(accessToken)
			parcel.writeString(refreshToken)
			parcel.writeValue(userId)
			parcel.writeString(phone)
			parcel.writeString(lastName)
			parcel.writeValue(twofactorAccepted)
			parcel.writeString(tokenType)
			parcel.writeValue(expiresIn)
			parcel.writeString(firstName)
			parcel.writeString(email)
			parcel.writeValue(termAccepted)
		}

		override fun create(parcel: Parcel): LoginResponse {
			return LoginResponse(parcel)
		}
	}
}
