import {
  Code,
  ExternalLink,
  GitBranch,
  Star,
  Users,
  Download,
  Scale,
  AlertTriangle,
} from "lucide-react";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { Navigation } from "@/components/navigation";
import Link from "next/link";

export default function GitHubPage() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-background to-muted/20">
      <Navigation />

      <div className="container mx-auto px-4 py-16 max-w-4xl">
        {/* Header */}
        <div className="text-center mb-16">
          <Badge variant="secondary" className="mb-4">
            Open Source
          </Badge>
          <h1 className="text-4xl font-bold mb-6">
            GitHub Repository & License
          </h1>
          <p className="text-xl text-muted-foreground leading-relaxed">
            Passcodes is completely open source. Explore the code, contribute,
            and make it your own.
          </p>
        </div>

        {/* Repository Info */}
        <section className="mb-16">
          <Card>
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <Code className="h-6 w-6 text-blue-600" />
                Repository Information
              </CardTitle>
              <CardDescription>
                Access the complete source code and contribute to the project
              </CardDescription>
            </CardHeader>
            <CardContent className="space-y-6">
              <div className="grid md:grid-cols-2 gap-6">
                <div>
                  <h4 className="font-semibold mb-2">Repository Status</h4>
                  <div className="flex items-center gap-2 mb-2">
                    <Badge variant="outline">Under Development</Badge>
                    <Badge variant="secondary">Android</Badge>
                  </div>
                  <p className="text-muted-foreground text-sm">
                    Active development with regular commits and updates
                  </p>
                </div>
                <div>
                  <h4 className="font-semibold mb-2">Tech Stack</h4>
                  <div className="flex flex-wrap gap-2 mb-2">
                    <Badge variant="outline">Kotlin</Badge>
                    <Badge variant="outline">Android SDK</Badge>
                    <Badge variant="outline">Room Database</Badge>
                  </div>
                  <p className="text-muted-foreground text-sm">
                    Modern Android development practices
                  </p>
                </div>
              </div>

              <div className="flex flex-col sm:flex-row gap-4">
                <Button className="gap-2" size="lg">
                  <Code className="h-5 w-5" />
                  View on GitHub
                  <ExternalLink className="h-4 w-4" />
                </Button>
                <Button variant="outline" className="gap-2" size="lg">
                  <Download className="h-5 w-5" />
                  Download Latest Release
                </Button>
                <Button variant="outline" className="gap-2" size="lg">
                  <GitBranch className="h-5 w-5" />
                  Fork Repository
                </Button>
              </div>
            </CardContent>
          </Card>
        </section>

        {/* Contributing */}
        <section className="mb-16">
          <h2 className="text-3xl font-bold mb-8 text-center">Contributing</h2>
          <div className="grid md:grid-cols-2 gap-6">
            <Card>
              <CardHeader>
                <Users className="h-8 w-8 text-green-600 mb-2" />
                <CardTitle>How to Contribute</CardTitle>
              </CardHeader>
              <CardContent className="space-y-3">
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-green-600 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    Fork the repository and create a feature branch
                  </p>
                </div>
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-green-600 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    Follow our coding standards and documentation
                  </p>
                </div>
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-green-600 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    Submit pull requests with detailed descriptions
                  </p>
                </div>
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-green-600 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    Report bugs and suggest features via issues
                  </p>
                </div>
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <Star className="h-8 w-8 text-yellow-600 mb-2" />
                <CardTitle>Areas We Need Help</CardTitle>
              </CardHeader>
              <CardContent className="space-y-3">
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-yellow-600 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    Security auditing and testing
                  </p>
                </div>
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-yellow-600 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    UI/UX improvements and customization options
                  </p>
                </div>
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-yellow-600 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    Documentation and user guides
                  </p>
                </div>
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-yellow-600 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    Testing on different Android versions
                  </p>
                </div>
              </CardContent>
            </Card>
          </div>
        </section>

        {/* MIT License */}
        <section className="mb-16">
          <h2 className="text-3xl font-bold mb-8 text-center">MIT License</h2>

          <Alert className="mb-6 border-amber-200 bg-amber-50 dark:border-amber-800 dark:bg-amber-950">
            <AlertTriangle className="h-4 w-4 text-amber-600 dark:text-amber-400" />
            <AlertTitle className="text-amber-800 dark:text-amber-200">
              Important Legal Notice
            </AlertTitle>
            <AlertDescription className="text-amber-700 dark:text-amber-300">
              Passcodes is provided "as is" without warranty of any kind. You
              are solely responsible for any data you share with the
              application.
            </AlertDescription>
          </Alert>

          <Card>
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <Scale className="h-6 w-6 text-purple-600" />
                MIT License Details
              </CardTitle>
              <CardDescription>
                Free to use, modify, and distribute with attribution
              </CardDescription>
            </CardHeader>
            <CardContent className="space-y-4">
              <div className="bg-muted p-4 rounded-lg font-mono text-sm">
                <p className="mb-4">
                  <strong>MIT License</strong>
                </p>
                <p className="mb-4">Copyright (c) 2024 Passcodes Project</p>
                <p className="mb-4">
                  Permission is hereby granted, free of charge, to any person
                  obtaining a copy of this software and associated documentation
                  files (the "Software"), to deal in the Software without
                  restriction, including without limitation the rights to use,
                  copy, modify, merge, publish, distribute, sublicense, and/or
                  sell copies of the Software, and to permit persons to whom the
                  Software is furnished to do so, subject to the following
                  conditions:
                </p>
                <p className="mb-4">
                  The above copyright notice and this permission notice shall be
                  included in all copies or substantial portions of the
                  Software.
                </p>
                <p className="text-muted-foreground">
                  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
                  KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
                  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
                  PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
                  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
                  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
                  OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
                  SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
                </p>
              </div>

              <div className="grid md:grid-cols-2 gap-4 mt-6">
                <div>
                  <h4 className="font-semibold mb-2 text-green-600">
                    What you CAN do:
                  </h4>
                  <ul className="space-y-1 text-muted-foreground text-sm">
                    <li>✓ Use commercially</li>
                    <li>✓ Modify the code</li>
                    <li>✓ Distribute copies</li>
                    <li>✓ Place warranty</li>
                    <li>✓ Use privately</li>
                  </ul>
                </div>
                <div>
                  <h4 className="font-semibold mb-2 text-red-600">
                    What you MUST do:
                  </h4>
                  <ul className="space-y-1 text-muted-foreground text-sm">
                    <li>• Include original license</li>
                    <li>• Include copyright notice</li>
                  </ul>
                  <h4 className="font-semibold mb-2 mt-4 text-gray-600">
                    What we DON'T provide:
                  </h4>
                  <ul className="space-y-1 text-muted-foreground text-sm">
                    <li>✗ Warranty</li>
                    <li>✗ Liability coverage</li>
                  </ul>
                </div>
              </div>
            </CardContent>
          </Card>
        </section>

        {/* Call to Action */}
        <section className="text-center">
          <Card>
            <CardContent className="pt-6">
              <h3 className="text-2xl font-bold mb-4">Ready to Contribute?</h3>
              <p className="text-muted-foreground mb-6">
                Join our community of developers working to make password
                management better for everyone.
              </p>
              <div className="flex flex-col sm:flex-row gap-4 justify-center">
                <Button size="lg" className="gap-2">
                  <Code className="h-5 w-5" />
                  Start Contributing
                  <ExternalLink className="h-4 w-4" />
                </Button>
                <Button variant="outline" size="lg" className="gap-2">
                  <Users className="h-5 w-5" />
                  Join Discussions
                </Button>
              </div>
            </CardContent>
          </Card>
        </section>
      </div>
    </div>
  );
}
