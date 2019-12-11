package base_bloc_generator.action

import base_bloc_generator.generator.BlocGeneratorFactory
import base_bloc_generator.generator.Generator
import com.intellij.lang.Language
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory

class GenerateBlocAction : AnAction(), GenerateBlocDialog.Listener {

    private lateinit var dataContext: DataContext

    override fun actionPerformed(e: AnActionEvent) {
        val dialog = GenerateBlocDialog(this)
        dialog.show()
    }

    override fun onGenerateBlocClicked(blocName: String?) {
        blocName?.let { name ->
            val generators = BlocGeneratorFactory.getBlocGenerators(name)
            generate(generators)
        }
    }

    override fun update(e: AnActionEvent) {
        e.dataContext.let {
            this.dataContext = it
            val presentation = e.presentation
            presentation.isEnabled = true
        }
    }
    private fun generate(mainSourceGenerators: List<Generator>) {
        val project = CommonDataKeys.PROJECT.getData(dataContext)
        val view = LangDataKeys.IDE_VIEW.getData(dataContext)
        val directory = view?.orChooseDirectory
        ApplicationManager.getApplication().runWriteAction {
            CommandProcessor.getInstance().executeCommand(
                project,
                {
                    mainSourceGenerators.forEach { createSourceFile(project!!, it, directory!!) }
                },
                "Generate a new Bloc",
                null
            )
        }
    }

    private fun createSourceFile(project: Project, generator: Generator, directory: PsiDirectory) {
        val fileName = generator.fileName()
        val psiFile = PsiFileFactory.getInstance(project)
            .createFileFromText(fileName, Language.ANY, generator.generate())
        directory.add(psiFile)
    }
}