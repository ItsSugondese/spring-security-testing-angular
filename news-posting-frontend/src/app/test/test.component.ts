import { Component, OnInit } from '@angular/core';
import { TestingService } from '../_test-service/testing.service';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit{

  message !: string;
  constructor(private testService : TestingService){

  }
  ngOnInit(): void {
    this.testService.getValue().subscribe(
      (result) => {
        this.message = result;
      }
    )
  }

  


}
