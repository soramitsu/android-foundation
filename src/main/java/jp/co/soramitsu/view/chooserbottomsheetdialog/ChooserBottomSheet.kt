package jp.co.soramitsu.view.chooserbottomsheetdialog

import android.app.Activity
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import jp.co.soramitsu.android_foundation.R
import jp.co.soramitsu.android_foundation.databinding.BottomSheetChooserBinding

class ChooserBottomSheet(
    context: Activity,
    title: Int,
    items: List<ChooserItem>
) : BottomSheetDialog(context, R.style.Theme_MaterialComponents_BottomSheetDialog) {

    init {
        val binding = BottomSheetChooserBinding.inflate(LayoutInflater.from(context), null, false)
            .also {
                setContentView(it.root)
            }

        binding.title.setText(title)

        val adapter = ChooserAdapter()
        binding.recyclerView.adapter = adapter
        adapter.submitList(items)
    }
}