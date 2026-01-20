# Contributing to LiteRead

Merci de vouloir contribuer à LiteRead! 🎉

## Code of Conduct

Soyez respectueux, inclusif et professionnel.

## Comment Contribuer?

### 1. Signaler un Bug 🐛

**Avant de créer une issue:**
- Vérifiez si elle existe déjà
- Testez la dernière version
- Consultez la documentation

**Quand vous signalez un bug:**
```
Titre: [BUG] Description courte

Description:
- Comportement observé
- Comportement attendu
- Version: X.X.X
- Appareil: Model (Android X.X)
- Étapes pour reproduire:
  1. ...
  2. ...
```

### 2. Suggérer une Feature 💡

```
Titre: [FEATURE] Description

Description:
- Problème/motivation
- Solution proposée
- Alternatives envisagées
```

### 3. Soumettre une Pull Request 🔧

#### Setup

```bash
# Fork & clone
git clone https://github.com/YOUR_USERNAME/literead.git
cd literead

# Créer une branche
git checkout -b feature/description

# Installer dépendances
./gradlew build
```

#### Développement

```bash
# Éditer votre code
# Tester régulièrement
./gradlew test
./gradlew lint

# Formatter le code
./gradlew ktlintFormat

# Vérifier les changements
git status
git diff
```

#### Guidelines

✅ **Faire:**
- One feature/fix per PR
- Clear, descriptive commit messages
- Test avant de soumettre
- Respecter la convention de code
- Documentation à jour
- Commenter le code complexe
- Utiliser des noms explicites

❌ **Éviter:**
- Multiples features/fixes
- Commits mal nommés
- Merge conflicts
- Code mal formaté
- Breaking changes sans discussion
- Dépendances lourdes

#### Code Style

```kotlin
// Kotlin Google Style Guide
// https://android.github.io/kotlin-guides/style.html

// 1. Noms clairs
fun openDocument()          // ✅ Good
fun od()                    // ❌ Bad

// 2. Fonctions courtes
fun loadData(): Data {      // Idéal < 30 lignes
    // ...
}

// 3. Types explicites
val documents: List<Document> = emptyList()  // Clair

// 4. Comentaires utiles
// Pourquoi, pas quoi (le code le dit)
suspend fun fetchData() {   // ✅ Commentaire utile
    // Cache les résultats pour 24h (business logic)
    delay(3600000)
}
```

#### Commits

```bash
# Format: type(scope): subject
# Exemple:
git commit -m "feat(reader): add bookmark support

- Implement bookmark creation
- Add bookmark storage in DB
- Update UI to show bookmarks

Closes #123"

# Types: feat, fix, docs, style, refactor, test, chore
```

#### PR Template

```markdown
## Description
Courte description des changements

## Type de PR
- [ ] Bug fix
- [ ] New feature
- [ ] Documentation
- [ ] Performance

## Testing
- [ ] Tests unitaires passent
- [ ] Tests d'instrumentation passent
- [ ] Pas de regressions

## Screenshots (si applicable)
[Insérer screenshots]

## Checklist
- [ ] Code formaté
- [ ] Documentation à jour
- [ ] Pas de breaking changes
- [ ] Tests complets

Closes #XXX
```

### 4. Code Review Process

1. **Automatic Checks**
   - Build passed
   - Lint passed
   - Tests passed
   - Code coverage OK

2. **Manual Review**
   - Architecture review
   - Code quality
   - Performance impact
   - Security concerns

3. **Approval**
   - 1+ approve required
   - All conversations resolved
   - Ready to merge

### 5. Projet Structure

```
literead/
├── app/
│   ├── src/
│   │   ├── main/          # Code source
│   │   ├── test/          # Unit tests
│   │   └── androidTest/   # Instrumentation tests
│   └── build.gradle.kts
└── ...
```

### 6. Documentation

**À mettre à jour:**
- Code comments
- README.md
- DOCUMENTATION.md
- CHANGELOG.md (unreleased section)

**Format Kotlin Docs:**
```kotlin
/**
 * Courte description (1 ligne)
 *
 * Description détaillée si nécessaire.
 *
 * @param param1 Description du paramètre
 * @return Description du retour
 * @throws Exception Description de l'exception
 *
 * Example:
 * ```
 * val result = myFunction(value)
 * ```
 */
```

### 7. Testing

```bash
# Unit tests
./gradlew test

# Instrumentation tests (appareil/émulateur nécessaire)
./gradlew connectedAndroidTest

# Code coverage
./gradlew jacocoTestReport

# All checks
./gradlew check
```

### 8. Performance

- APK size: target < 10MB
- Memory: < 100MB normal usage
- Startup time: < 2s
- Frame rate: 60 FPS smooth

### 9. Security

- No hardcoded secrets
- No dependency vulnerabilities
- No sensitive data in logs
- Use Android Security Best Practices

### 10. Getting Help

- **Documentation**: [DOCUMENTATION.md](DOCUMENTATION.md)
- **Development**: [DEVELOPMENT.md](DEVELOPMENT.md)
- **Issues**: [GitHub Issues](https://github.com/wabosphere/literead/issues)
- **Discussions**: [GitHub Discussions](https://github.com/wabosphere/literead/discussions)

---

## Release Process

1. **Version Bump**
   ```bash
   # Update version in build.gradle.kts
   # Update CHANGELOG.md
   ```

2. **Testing**
   ```bash
   ./gradlew build test connectedAndroidTest
   ```

3. **Create Tag**
   ```bash
   git tag v1.x.x
   git push origin v1.x.x
   ```

4. **GitHub Release**
   - Auto-triggers release workflow
   - APK & AAB built & signed
   - Release notes auto-generated

---

## License

By contributing, you agree that your contributions will be licensed under its MIT License.

---

**Merci de votre contribution! 🙏**

Pour toute question: [GitHub Discussions](https://github.com/wabosphere/literead/discussions)
