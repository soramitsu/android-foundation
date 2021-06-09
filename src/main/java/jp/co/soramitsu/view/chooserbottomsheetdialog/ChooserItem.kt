package jp.co.soramitsu.view.chooserbottomsheetdialog

data class ChooserItem(
    val title: Int,
    val icon: Int,
    val clickHandler: () -> Unit
)