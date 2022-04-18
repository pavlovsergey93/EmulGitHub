package com.gmail.pavlovsv93.emulgithub.data

import android.os.Parcel
import android.os.Parcelable

data class AccountGitHub(val token: String, val login: String, var password: String?, var list: List<AccountRepo>? = null) :
	Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString()!!,
		parcel.readString()!!,
		parcel.readString(),
		TODO("list")
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(token)
		parcel.writeString(login)
		parcel.writeString(password)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<AccountGitHub> {
		override fun createFromParcel(parcel: Parcel): AccountGitHub {
			return AccountGitHub(parcel)
		}

		override fun newArray(size: Int): Array<AccountGitHub?> {
			return arrayOfNulls(size)
		}
	}
}
