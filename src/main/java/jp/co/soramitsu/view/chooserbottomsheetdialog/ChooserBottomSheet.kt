package jp.co.soramitsu.view.chooserbottomsheetdialog

import android.app.Activity
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import jp.co.soramitsu.android_foundation.R
import jp.co.soramitsu.android_foundation.databinding.BottomSheetChooserBinding

class ChooserBottomSheet(
    context: Activity,
    title: Int,
    items: List<ChooserItem>,
    dividerColorResource: Int
) : BottomSheetDialog(context, R.style.BottomSheetDialog) {

    init {
        val binding = BottomSheetChooserBinding.inflate(LayoutInflater.from(context), null, false)
            .also {
                setContentView(it.root)
            }

        binding.title.setText(title)

        val adapter = ChooserAdapter(dividerColorResource)
        binding.recyclerView.adapter = adapter
        binding.divider1.setBackgroundResource(dividerColorResource)
        adapter.submitList(items)
    }
}
