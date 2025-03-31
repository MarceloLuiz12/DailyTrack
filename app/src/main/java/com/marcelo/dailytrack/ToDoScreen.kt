import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.marcelo.dailytrack.BottomNavItem
import com.marcelo.dailytrack.MainViewModel
import com.marcelo.todokit.domain.enums.TaskStatusEnum
import com.marcelo.todokit.domain.model.TaskModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TodoListApp() {
    val viewModel: MainViewModel = koinViewModel()
    val navController = rememberNavController()
    val success by viewModel.success.collectAsState()
    LaunchedEffect(success) {
        viewModel.getAllTasks()
    }
    val tasks by viewModel.tasks.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val items = listOf(BottomNavItem.TODO, BottomNavItem.PROGRESS, BottomNavItem.DONE)
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = { navController.navigate(screen.route) }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task", tint = Color.White)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomNavItem.TODO.route,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.TODO.route) { TodoScreen(BottomNavItem.TODO.label, tasks) }
            composable(BottomNavItem.PROGRESS.route) { TodoScreen(BottomNavItem.PROGRESS.label, tasks) }
            composable(BottomNavItem.DONE.route) { TodoScreen(BottomNavItem.DONE.label, tasks) }
        }
    }

    if (showDialog) {
        AddTaskDialog(
            onDismiss = { showDialog = false },
            onAddTask = { id, title, description, status ->
                viewModel.addTask(title, description, status)
                showDialog = false
            }
        )
    }
}

@Composable
fun TodoScreen(title: String, tasks: List<TaskModel>) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                Text(text = "Title: ${task.title}, Status: ${task.status} description: ${task.description}")
            }
        }
    }
}

@Composable
fun AddTaskDialog(
    onDismiss: () -> Unit,
    onAddTask: (Long, String, String, TaskStatusEnum) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedStatus by remember { mutableStateOf(TaskStatusEnum.TODO) }
    var expanded by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Task") },
        text = {
            Column {
                Text("Title")
                BasicTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    decorationBox = { innerTextField -> innerTextField() }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Description")
                BasicTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    decorationBox = { innerTextField -> innerTextField() }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Status")
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                ) {
                    TaskStatusEnum.entries.forEach { status ->
                        DropdownMenuItem(
                            onClick = {
                                selectedStatus = status
                                expanded = false
                            },
                            text = {
                                Text(status.name)
                            }
                        )
                    }
                }
            }
        },
        confirmButton = {
            val randomId = (1..1000).random().toLong()
            TextButton(
                onClick = {
                    onAddTask(randomId, title, description, selectedStatus)
                }
            ) {
                Text("Add Task")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTodoListApp() {
    TodoListApp()
}
