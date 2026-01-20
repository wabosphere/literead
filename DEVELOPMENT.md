# Guide de Développement - LiteRead

## 📋 Table des matières

1. [Environnement de développement](#environnement-de-développement)
2. [Architecture MVVM](#architecture-mvvm)
3. [Conventions de code](#conventions-de-code)
4. [Workflow de développement](#workflow-de-développement)
5. [Debugging & Testing](#debugging--testing)
6. [Performance](#performance)
7. [Checklist avant commit](#checklist-avant-commit)

---

## 🛠️ Environnement de développement

### Installation

1. **Android Studio** (Arctic Fox ou supérieur)
   ```bash
   # Sur Linux
   sudo apt install android-studio
   
   # Sur macOS
   brew install android-studio
   ```

2. **SDK Android**
   - Target SDK: 34
   - Min SDK: 21 (Android 5.0)
   - Compilé avec Kotlin 1.9.22

3. **Java Development Kit**
   - JDK 11 ou supérieur
   - Configurez `JAVA_HOME`

### Configuration du Projet

```bash
# Cloner le repo
git clone https://github.com/literead/literead.git
cd literead

# Synchroniser Gradle
./gradlew sync

# Build debug
./gradlew assembleDebug

# Installer sur émulateur/appareil
./gradlew installDebug
```

---

## 🏗️ Architecture MVVM

### Pattern MVVM

```
┌─────────────────────────────────────────────────┐
│                   VIEW (UI)                     │
│  Activity / Fragment / Adapter / Custom View    │
└──────────────────────┬──────────────────────────┘
                       │ observes
                       ▼
┌─────────────────────────────────────────────────┐
│              VIEWMODEL (Logic)                  │
│   LibraryViewModel / ReaderViewModel / ...      │
└──────────────────────┬──────────────────────────┘
                       │ uses
                       ▼
┌─────────────────────────────────────────────────┐
│          REPOSITORY (Data Access)               │
│         DocumentRepository                      │
└──────────────────────┬──────────────────────────┘
                       │ accesses
                       ▼
┌─────────────────────────────────────────────────┐
│       MODEL & DATA SOURCES (Persistence)        │
│  Room Database / SharedPreferences / Files      │
└─────────────────────────────────────────────────┘
```

### Flux de données

```kotlin
// 1. View déclenche une action
binding.button.setOnClickListener {
    viewModel.loadDocuments()
}

// 2. ViewModel appelle le Repository
fun loadDocuments() {
    viewModel.launch {
        val docs = repository.getAllDocuments()
        _documents.value = docs
    }
}

// 3. View observe les changements
viewModel.documents.observe(this) { docs ->
    adapter.submitList(docs)
}
```

---

## 📝 Conventions de code

### Nommage

```kotlin
// Classes
class DocumentAdapter { }
class LibraryFragment { }

// Propriétés
private val _documents = MutableLiveData<List<Document>>()
val documents: LiveData<List<Document>> = _documents

// Méthodes
fun openDocument(id: Long)
private fun setupUI()
suspend fun loadData()

// Constantes
companion object {
    private const val TAG = "LibraryFragment"
}
```

### Imports Kotlin

```kotlin
// Utiliser les extension functions
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

// Au lieu de
// import android.os.Handler
```

### Coroutines

```kotlin
// ✅ BON: Utiliser viewModelScope ou lifecycleScope
viewModel.launch {
    val result = repository.fetchData()
    _state.value = result
}

// ❌ MAUVAIS: Créer des scopes personnalisés
GlobalScope.launch {
    // Éviter
}
```

### Collections

```kotlin
// ✅ BON: Immutable par défaut
fun getDocuments(): LiveData<List<Document>> = _documents

// ❌ MAUVAIS: Retourner des collections mutables
fun getDocuments(): List<Document> = _documents.value ?: emptyList()
```

### Null Safety

```kotlin
// ✅ BON: Utiliser l'elvis operator
val title = document?.title ?: "Unknown"

// ✅ BON: Safe calls
document?.open()

// ❌ MAUVAIS: Eviter les non-null assertions
val title = document!!.title
```

### Documentation

```kotlin
/**
 * Charge tous les documents de la bibliothèque
 *
 * @return LiveData contenant la liste des documents
 *
 * @throws IOException si la lecture échoue
 */
fun loadDocuments(): LiveData<List<Document>>

// Pour les paramètres complexes
/**
 * Ouvre un document
 *
 * @param document Le document à ouvrir
 * @param options Options d'ouverture:
 *        - resume: reprendre depuis last read page
 *        - fullscreen: mode plein écran
 */
fun openDocument(document: Document, options: Map<String, Boolean>)
```

---

## 🔄 Workflow de développement

### 1. Créer une nouvelle feature

```bash
# Créer une branche
git checkout -b feature/ma-nouvelle-feature

# Assurez-vous que le code compile
./gradlew build
```

### 2. Structure d'une nouvelle feature

Si vous ajoutez une nouvelle screen:

```
Créer dans cet ordre:

1. data/model/NewModel.kt          # Modèle de données
2. data/db/NewDao.kt               # DAO si nécessaire
3. data/repository/               # Ajouter methods si nécessaire
4. viewmodel/NewViewModel.kt       # ViewModel
5. ui/fragment/NewFragment.kt      # Ou Activity
6. res/layout/fragment_new.xml     # Layout XML
7. ui/adapter/NewAdapter.kt        # Adapter si liste
```

### 3. Ajouter une dépendance

```bash
# Éditer app/build.gradle.kts
dependencies {
    implementation("group:artifact:version")
}

# Rebuild
./gradlew build
```

### 4. Testing

```bash
# Exécuter les tests
./gradlew test

# Tests d'instrumentation (sur appareil)
./gradlew connectedAndroidTest

# Code coverage
./gradlew jacocoTestReport
```

### 5. Commit & Push

```bash
# Vérifier les changements
git status
git diff

# Committer
git add .
git commit -m "feat: Ajouter feature X

- Description du changement 1
- Description du changement 2

Closes #123"

# Pousser
git push origin feature/ma-nouvelle-feature
```

### 6. Pull Request

1. Ouvrir une PR sur GitHub
2. Description claire des changements
3. Screenshots si UI change
4. Attendre la review

---

## 🐛 Debugging & Testing

### Debugging

#### Logcat

```bash
# Afficher tous les logs
./gradlew assembleDebug
# Puis Logcat dans Android Studio

# Ou en terminal
adb logcat
```

#### Breakpoints

1. Cliquer à gauche du numéro de ligne
2. Run → Debug 'app'
3. Le code s'arrête au breakpoint

#### Propriétés

```kotlin
// Ajouter du logging
Log.d("TAG", "Message: $variable")

// Better: Utiliser des extensions
class MyClass {
    companion object {
        private val TAG = MyClass::class.simpleName
    }
}
Log.d(TAG, "Debug message")
```

### Unit Tests

```kotlin
// app/src/test/java/com/literead/

@RunWith(JUnit4::class)
class DocumentRepositoryTest {
    
    private lateinit var repository: DocumentRepository
    
    @Before
    fun setup() {
        // Initialize test objects
    }
    
    @Test
    fun testLoadDocument() {
        // Arrange
        val expectedDoc = Document(id = 1, title = "Test")
        
        // Act
        val result = repository.loadDocument(1)
        
        // Assert
        assertEquals(expectedDoc, result)
    }
}
```

### Instrumentation Tests

```kotlin
// app/src/androidTest/java/com/literead/

@RunWith(AndroidJUnit4::class)
class LibraryFragmentTest {
    
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    
    @Test
    fun testLibraryDisplaysBooks() {
        // Arrange
        onView(withId(R.id.libraryFragment)).check(matches(isDisplayed()))
        
        // Act & Assert
        onView(withText("Sample Book"))
            .check(matches(isDisplayed()))
    }
}
```

---

## ⚡ Performance

### Optimisation Mémoire

✅ **Faire:**
```kotlin
// Libérer les ressources
override fun onDestroy() {
    super.onDestroy()
    _binding = null
}

// Utiliser weak references si nécessaire
private var mCallback: WeakReference<Callback>? = null
```

❌ **Éviter:**
```kotlin
// Memory leaks
GlobalScope.launch {
    // Éviter
}

// Static references
companion object {
    var activity: Activity? = null  // Mauvais!
}
```

### Optimisation CPU

✅ **Faire:**
```kotlin
// Utiliser ListAdapter avec DiffUtil
class DocumentAdapter : ListAdapter<Document, ViewHolder>(DIFF_CALLBACK) {
    // Efficient updates
}

// Lazy initialization
private val heavyObject: ExpensiveObject by lazy {
    ExpensiveObject()
}
```

❌ **Éviter:**
```kotlin
// Boucles inefficaces
for (i in 0 until list.size) {
    list[i].process()  // Accès répété
}

// Créations multiples d'objets
fun expensiveOperation() {
    val newList = mutableListOf<Item>()
    for (item in items) {
        newList.add(item)  // Inefficace
    }
}
```

### Monitoring

```bash
# Profiler Android Studio
# Android Studio → Profiler → CPU/Memory

# Ou via Gradle
./gradlew profileRelease
```

---

## ✅ Checklist avant commit

- [ ] Code compile sans erreur
- [ ] Pas d'avertissements (warnings)
- [ ] Tests passent
- [ ] Code formaté proprement
- [ ] Pas de code commenté inutile
- [ ] Pas de logs de debug
- [ ] Documentation à jour
- [ ] Strings externalisées en ressources
- [ ] Pas de hardcoded values
- [ ] Compatibilité API 21+
- [ ] ProGuard compatible
- [ ] Pas de new permissions requises

---

## 📚 Ressources

- [Android Developer Guide](https://developer.android.com/guide)
- [Kotlin Documentation](https://kotlinlang.org/docs/)
- [Room Persistence Library](https://developer.android.com/training/data-storage/room)
- [LiveData & ViewModel](https://developer.android.com/topic/libraries/architecture/livedata)
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Material Design 3](https://m3.material.io/)

---

**Questions ou problèmes?** Créez une issue sur GitHub ou consultez la documentation.
