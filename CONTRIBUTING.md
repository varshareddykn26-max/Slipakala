# ShilpaKala - Contributing Guide

## 🤝 How to Contribute

We welcome contributions from developers, designers, and artisans! Here's how to get involved.

---

## 📋 Getting Started

### Prerequisites
- Android Studio 2023.1+
- Git knowledge
- Kotlin programming experience
- Enthusiasm for artisan empowerment

### Fork & Clone
```bash
# Fork the repo on GitHub
# Clone your fork
git clone https://github.com/YOUR_USERNAME/ShilpaKala.git

# Add upstream remote
git remote add upstream https://github.com/originalrepo/ShilpaKala.git

# Keep fork updated
git fetch upstream
git merge upstream/main
```

---

## 🐛 Reporting Issues

### Before Reporting
- [ ] Check existing issues (might already be reported)
- [ ] Try latest version
- [ ] Test on emulator & real device

### Issue Template
```markdown
## Title
Brief description

## Device Info
- Android Version: 
- Device Model:
- App Version:

## Steps to Reproduce
1. 
2. 
3. 

## Expected Behavior
What should happen

## Actual Behavior
What actually happens

## Screenshots/Videos
Attach if applicable

## Logs
```
adb logcat > logs.txt
```
Include relevant logs
```

---

## 🎯 Feature Requests

### Suggest a Feature
```markdown
## Feature Title
One-line description

## Use Case
Why this feature is needed

## Proposed Solution
How you'd like it implemented

## Alternatives
Other ways to achieve this

## Example Code/Mockup
Visual representation if applicable
```

---

## 💻 Development Workflow

### 1. Create Feature Branch
```bash
# From main branch
git checkout -b feature/short-description

# Naming conventions:
# feature/camera-enhancement
# bugfix/permission-issue
# docs/api-reference
# refactor/image-processor
```

### 2. Make Changes

#### Code Style
- Follow Kotlin conventions
- Use meaningful variable names
- Add comments for complex logic
- Keep functions focused

#### File Organization
```
src/
├── ui/
│   ├── screens/
│   └── components/
├── utils/
├── data/
└── viewmodels/
```

#### Example: Adding a New Feature
```kotlin
// 1. Create ViewModel
package com.shilpakala.app.ui.feature

class FeatureViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun loadData() {
        viewModelScope.launch {
            // Implementation
        }
    }
}

// 2. Create Composable
@Composable
fun FeatureScreen(
    viewModel: FeatureViewModel = viewModel(),
    onNavigate: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Success -> SuccessScreen()
        is UiState.Error -> ErrorScreen()
    }
}

// 3. Add Navigation
composable("feature") {
    FeatureScreen(onNavigate = { /* ... */ })
}
```

### 3. Test Your Changes

#### Unit Tests
```kotlin
@Test
fun testImageProcessing() {
    val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
    val result = ImageProcessor.addBranding(bitmap, "Test", "Teak", "100")
    
    assertNotNull(result)
    assertEquals(bitmap.width, result.width)
}
```

#### Manual Testing
- [ ] Test on emulator (API 24, 29, 34)
- [ ] Test on real device
- [ ] Test different screen sizes
- [ ] Test all user flows

### 4. Commit Changes
```bash
# Stage changes
git add .

# Commit with descriptive message
git commit -m "feat: add feature name

- Detailed description of changes
- Breaking changes (if any)
- Closes #issue_number"

# Commit message format:
# feat: new feature
# fix: bug fix
# docs: documentation
# style: formatting
# refactor: code restructuring
# test: adding tests
# chore: maintenance
```

### 5. Keep Fork Updated
```bash
# Fetch latest changes
git fetch upstream

# Rebase on main
git rebase upstream/main

# Or merge if preferred
git merge upstream/main
```

### 6. Push & Create Pull Request
```bash
# Push to your fork
git push origin feature/your-feature

# Create PR on GitHub
# - Link related issues
# - Describe changes
# - Request review
```

---

## ✅ Pull Request Checklist

- [ ] Feature/fix is complete and tested
- [ ] All unit tests pass
- [ ] Manual testing done on device
- [ ] Code follows style guide
- [ ] Documentation updated (if needed)
- [ ] No console errors/warnings
- [ ] Commit messages are clear
- [ ] Branch is up-to-date with main

---

## 📝 Documentation

### Update Docs For:
- New features
- API changes
- Breaking changes
- New dependencies

### Documentation Files
- `README.md` - Overview
- `SETUP_GUIDE.md` - Installation
- `API_REFERENCE.md` - Component docs
- `ARCHITECTURE.md` - Technical details
- `ROADMAP.md` - Future plans

### Doc Format
```markdown
## Feature Name

### Overview
Brief description

### Usage
```kotlin
// Code example
```

### Parameters
- param1: Description
- param2: Description

### Returns
Description

### Example
Complete working example
```

---

## 🎨 UI/UX Contributions

### Design Guidelines
- Material Design 3
- Accessibility compliance (WCAG AA)
- Dark mode support
- Consistent spacing (8dp grid)

### Color Palette
```
Primary: #FFD700 (Gold)
Dark: #2C2C2C
Background: #FFFFFF
Surface: #F5F5F5
Error: #FF5252
```

### Testing UI Changes
- [ ] Check on light/dark mode
- [ ] Test accessibility (screen readers)
- [ ] Verify on different screen sizes
- [ ] Check animations performance

---

## 🔄 Code Review Process

### What Reviewers Look For
- ✅ Code quality & style
- ✅ Functionality correctness
- ✅ Performance impact
- ✅ Documentation clarity
- ✅ Test coverage

### Responding to Feedback
- Thank reviewers for feedback
- Explain your reasoning if needed
- Make requested changes
- Request re-review

### Example Review Comment Response
```
Reviewer: "Consider using StateFlow instead"

Your Response: "Good point! Updated to use StateFlow
for reactive state management. Thanks for the feedback!"

# Make the change, then push
git push origin feature/your-feature
```

---

## 🧪 Testing Requirements

### Unit Tests
```kotlin
// Test utility functions
@Test
fun testImageProcessorAddBranding() { }

@Test
fun testFileUtilsSaveImage() { }

@Test
fun testHeritageLabel() { }
```

### Integration Tests
```kotlin
// Test screen interactions
@Test
fun testCameraScreenFlow() { }

@Test
fun testBrandingWorkflow() { }
```

### Running Tests
```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Run specific test
./gradlew testDebugUnitTest --tests "com.shilpakala.*"
```

---

## 📊 Code Quality Standards

### Kotlin Style Guide
- Follow [Kotlin conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful names
- Keep functions small (max 30 lines)
- Add kdoc for public functions

### Example: Well-Structured Code
```kotlin
/**
 * Processes an image with branding information.
 *
 * @param original The original bitmap from camera
 * @param artisanName The artisan's name
 * @param woodType Type of wood used
 * @param price Product price
 * @return Branded bitmap ready for saving
 * @throws IllegalArgumentException if inputs are invalid
 */
fun addBranding(
    original: Bitmap,
    artisanName: String,
    woodType: String,
    price: String
): Bitmap {
    require(artisanName.isNotEmpty()) { "Artisan name cannot be empty" }
    require(price.isNotEmpty()) { "Price cannot be empty" }

    return original.copy(Bitmap.Config.ARGB_8888, true).apply {
        // Implementation with comments for complex parts
    }
}
```

---

## 🚀 Performance Checklist

Before submitting:
- [ ] No memory leaks
- [ ] Bitmap operations optimized
- [ ] Camera preview smooth (60 FPS)
- [ ] Gallery loads in <500ms
- [ ] No ANR (Application Not Responding)

### Profiling Tools
```bash
# Profile app performance
./gradlew profileDebug

# Check memory usage
adb shell dumpsys meminfo com.shilpakala.app
```

---

## 🎓 Contributor Levels

### Level 1: First-Time Contributor
- Fix simple bugs
- Improve documentation
- Add test cases
- Fix typos

**Tasks**: Issues labeled `good-first-issue`

### Level 2: Regular Contributor
- Implement medium features
- Refactor components
- Optimize performance
- Review PRs

**Tasks**: Issues labeled `help-wanted`

### Level 3: Core Maintainer
- Design major features
- Plan architecture
- Make release decisions
- Manage community

**Tasks**: Issues labeled `core` or created by maintainers

---

## 📈 Recognition

### Contribution Recognition
- Added to CONTRIBUTORS.md
- Mentioned in release notes
- GitHub contributor badge
- Exclusive Discord role

### Top Contributors
- Listed on GitHub profile
- Featured in project README
- Invited to core team

---

## 🎁 Ideas for Contribution

### Code Contributions
- [ ] Add background blur feature
- [ ] Implement batch processing
- [ ] Add image filters
- [ ] Optimize image loading
- [ ] Write unit tests

### Documentation
- [ ] Add video tutorials
- [ ] Create troubleshooting guide
- [ ] Write API examples
- [ ] Translate docs to other languages

### Design
- [ ] Create app mockups
- [ ] Design UI improvements
- [ ] Create marketing materials
- [ ] Design app icons

### Community
- [ ] Answer questions
- [ ] Help debug issues
- [ ] Organize meetups
- [ ] Translate app to new language

---

## ⚠️ Code of Conduct

### Be Respectful
- Treat all contributors with respect
- Welcome diverse perspectives
- Address conflicts professionally

### Be Helpful
- Help newer contributors
- Share knowledge
- Provide constructive feedback

### Be Inclusive
- Use inclusive language
- Welcome all skill levels
- Support underrepresented groups

---

## 🏆 Contributor Agreement

By contributing, you agree that:
- Your contribution can be used under the project's license
- You have the right to contribute this code
- Your contributions follow our guidelines
- You grant perpetual rights to your contributions

---

## 📞 Getting Help

### Questions?
- Check existing discussions
- Ask in GitHub Discussions
- Email maintainers
- Join Discord community

### Stuck?
- Review documentation
- Check similar PRs
- Ask for help in comments
- Reach out to maintainers

---

## 🎉 Thank You!

Your contributions make ShilpaKala better for artisans worldwide. 

**Happy Contributing!** 🚀

---

**Last Updated:** May 2026
**Maintainers**: [@contributor1, @contributor2]
