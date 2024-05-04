package com.example.flashwiz_fe.presentation.screen.group

import StudyGroupViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashwiz_fe.presentation.components.BackIconComponent

@Composable
fun AddStudyGroupScreen(
    onNavigateBack: () -> Unit
) {
    val viewModel: StudyGroupViewModel = viewModel()
//    val userId = viewModel.userId
    var groupName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "Add Group",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = groupName,
            onValueChange = { groupName = it },
            label = { Text("Group Name") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
//            BackIconComponent(onBackClick = onNavigateBack)
//            Button(
//                onClick = {
//                    viewModel.addGroup(groupName) { isSuccess ->
//                        if (isSuccess) {
//                            onNavigateBack()
//                        } else {
//                            // Xử lý khi thêm nhóm không thành công
//                        }
//                    }
//                }
//            )
//            {
//                Text("Add Group")
//            }
        }
    }
}
