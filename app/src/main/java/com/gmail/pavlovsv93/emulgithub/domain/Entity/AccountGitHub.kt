package com.gmail.pavlovsv93.emulgithub.domain.Entity

import android.os.Parcel
import android.os.Parcelable

data class AccountGitHub(
	val id: Int,
	val avatar: String,
	val login: String,
	val htmlUrl: String,
	var reposListUrl: String
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.readString().toString(),
		parcel.readString().toString(),
		parcel.readString().toString(),
		parcel.readString().toString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeInt(id)
		parcel.writeString(avatar)
		parcel.writeString(login)
		parcel.writeString(htmlUrl)
		parcel.writeString(reposListUrl)
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
