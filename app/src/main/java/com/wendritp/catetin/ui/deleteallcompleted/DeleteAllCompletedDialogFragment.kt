package com.wendritp.catetin.ui.deleteallcompleted

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteAllCompletedDialogFragment : DialogFragment() {

    private val viewModel: DeleteAllCompletedViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Penghapusan")
            .setMessage("Apakah Anda Yakin Untuk Menghapus Catatan Yang Sudah Selesai?")
            .setNegativeButton("Batal", null)
            .setPositiveButton("Yakin") { _, _ ->
                viewModel.onConfirmClick()
            }
            .create()
}
